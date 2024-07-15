import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject, Injectable } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ErrorMessagePipe, TypeValidator, UppercaseValidator } from '@my/core';
import { Observable } from 'rxjs';
import { NotificationService, NotificationType } from 'src/app/common-services';
import { environment } from 'src/environments/environment';

export abstract class RESTDAOService<T, K> {
  protected baseUrl = environment.apiURL;
  protected http = inject(HttpClient)
  constructor(entidad: string, protected option = {}) {
  this.baseUrl += entidad;
  }
  query(extras = {}): Observable<Array<T>> {
  return this.http.get<Array<T>>(this.baseUrl, Object.assign({}, this.option, extras));
  }
  get(id: K, extras = {}): Observable<T> {
  return this.http.get<T>(`${this.baseUrl}/${id}`, Object.assign({}, this.option, extras));
  }
  add(item: T, extras = {}): Observable<T> {
  return this.http.post<T>(this.baseUrl, item, Object.assign({}, this.option, extras));
  }
  change(id: K, item: T, extras = {}): Observable<T> {
  return this.http.put<T>(`${this.baseUrl}/${id}`, item, Object.assign({}, this.option, extras));
  }
  remove(id: K, extras = {}): Observable<T> {
  return this.http.delete<T>(`${this.baseUrl}/${id}`, Object.assign({}, this.option, extras));
  }
}

@Injectable({providedIn: 'root'})
export class PeopleDAOService extends RESTDAOService<any, any> {
  constructor() {
    super('personas')
  }
}

@Component({
  selector: 'app-forms',
  standalone: true,
  imports: [CommonModule, FormsModule, ErrorMessagePipe, TypeValidator, UppercaseValidator],
  templateUrl: './forms.component.html',
  styleUrl: './forms.component.css',
})
export class FormsComponent {
  mode: 'add' | 'edit' = 'add'
  element: any = {
    id: 0,
    name: 'Pepito',
    lastname: 'Grillo',
    email: 'pgrillo@example.com',
    age: 99,
    date: '2024/07/12',
    conflictive: true,
  }

  constructor(private dao: PeopleDAOService, private notify: NotificationService) {} 

  add() {
    this.element = {};
    this.mode = 'add';
  }

  edit(key: number) {
    this.dao.get(key).subscribe({
      next: data => {
        this.element = data
        this.mode = 'edit'
      },
      error: err => this.notify.add(err.message)
    })
  }

  cancel() {}

  send() {
    switch (this.mode) {
      case 'add':
        this.dao.add(this.element).subscribe({
          next: () => {
            this.notify.add('Persona creada', NotificationType.info)
            this.cancel()
          },
          error: err => this.notify.add(err.message)
        })
        break
      case 'edit':
        this.dao.change(this.element.id, this.element).subscribe({
          next: () => {
            this.notify.add('Persona modificada', NotificationType.info)
            this.cancel()
          },
          error: err => this.notify.add(err.message)
        })
        break
    }
  }
}
