import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

export type ModoCRUD = 'list' | 'add' | 'edit' | 'view' | 'delete';

@Injectable({
  providedIn: 'root',
})
export class ActorsViewModelService {
  protected mode: ModoCRUD = 'list';
  protected actorsList: Array<any> = [];
  protected element: any = {};
  protected originalId: any = null;

  constructor(protected dao: ActorsDAOService) { }

  public get Mode(): ModoCRUD { return this.mode; }
  public get List(): Array<any> { return this.actorsList; }
  public get Element(): any { return this.element; }

  public list(): void {
    this.dao.query().subscribe({
      next: data => {
        this.actorsList = data;
        this.mode = 'list';
      },
      error: err => this.handleError(err)
    });
  }

  public add(): void {
    this.element = {};
    this.mode = 'add';
  }

  public edit(key: any): void {
    this.dao.get(key).subscribe({
      next: data => {
        this.element = data;
        this.originalId = key;
        this.mode = 'edit';
      },
      error: err => this.handleError(err)
    });
  }

  public view(key: any): void {
    this.dao.get(key).subscribe({
      next: data => {
        this.element = data;
        this.mode = 'view';
      },
      error: err => this.handleError(err)
    });
  }

  public delete(key: any): void {
    if (!window.confirm('Are you sure?')) { return; }

    this.dao.remove(key).subscribe({
      next: () => this.list(),
      error: err => this.handleError(err)
    });
  }

  public cancel(): void {
    this.element = {};
    this.originalId = null;
    this.list();
  }

  public send(): void {
    switch (this.mode) {
      case 'add':
        this.dao.add(this.element).subscribe({
          next: () => this.cancel(),
          error: err => this.handleError(err)
        });
        break;
      case 'edit':
        this.dao.change(this.originalId, this.element).subscribe({
          next: () => this.cancel(),
          error: err => this.handleError(err)
        });
        break;
      case 'view':
        this.cancel();
        break;
    }
  }

  clear() {
    this.actorsList = [];
  }

  handleError(err: any) {
    let msg = '';
    switch (err.status) {
      case 0: msg = err.message; break;
      case 404: msg = `ERROR ${err.status}: ${err.statusText}`; break;
      default:
        msg = `ERROR ${err.status}: ${err.error?.['title'] ?? err.statusText}. ${err.error?.['detail'] ? `Details: ${err.error['detail']}` : ''}`;
        break;
    }
  }
}

export abstract class RESTDAOService<T, K> {
  protected baseUrl = environment.apiURL;
  protected http: HttpClient = inject(HttpClient);

  constructor(entity: string, protected option = {}) {
    this.baseUrl += entity;
  }

  query(): Observable<Array<T>> {
    return this.http.get<Array<T>>(this.baseUrl, this.option);
  }

  get(id: K): Observable<T> {
    return this.http.get<T>(`${this.baseUrl}/${id}`, this.option);
  }

  add(item: T): Observable<T> {
    return this.http.post<T>(this.baseUrl, item, this.option);
  }

  change(id: K, item: T): Observable<T> {
    return this.http.put<T>(`${this.baseUrl}/${id}`, item, this.option);
  }

  remove(id: K): Observable<T> {
    return this.http.delete<T>(`${this.baseUrl}/${id}`, this.option);
  }
}

@Injectable({
  providedIn: 'root',
})
export class ActorsDAOService extends RESTDAOService<any, any> {
  constructor() {
    super('actors/v1');
  }
}
