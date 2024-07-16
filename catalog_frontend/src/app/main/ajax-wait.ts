import { Component, Injectable, DoCheck, inject } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHandlerFn } from '@angular/common/http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AjaxWaitService {
  private cont: number = 0;

  public get Visible() { return this.cont > 0; }
  public get Hidden() { return !this.Visible; }

  public Show(): void { this.cont++; }
  public Hide(): void {
    if (this.cont > 0) { this.cont--; }
  }
}

@Injectable({ providedIn: 'root' })
export class AjaxWaitInterceptor implements HttpInterceptor {
  constructor(private srv: AjaxWaitService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.srv.Show();
    return next.handle(req)
      .pipe(
        finalize(() => this.srv.Hide())
      );
  }
}

export function ajaxWaitInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
  const srv: AjaxWaitService = inject(AjaxWaitService);
  srv.Show();
  return next(req).pipe(finalize(() => srv.Hide()));
}

@Component({
    selector: 'app-ajax-wait',
    template: `
  <div [hidden]="Hidden">
    <div class="ajax-wait"></div>
    <div class="loader"></div>
  </div>`,
    styles: [`
    .ajax-wait {
      position: fixed;
      background-color: black;
      left: 0;
      top: 0;
      width: 100vw;
      height: 100vh;
      opacity: 0.3;
      z-index:100;
    }
    img {
      position: fixed;
      left: 45%;
      top: 45%;
      width: 10%;
      height: auto;
      opacity: 1;
      z-index:101;
    }
    .loader {
        border: 16px dotted #1a93fd;
        border-radius: 50%;
        animation: spin 5s linear infinite;
        position: fixed;
        left: 45%;
        top: 45%;
        width: 80px;
        height: 80px;
        z-index:101;
        opacity: 1;
      }

      @keyframes spin {
        0% { transform: rotate(0deg); }
        100% { transform: rotate(360deg); }
      }
  `],
    standalone: true,
})
export class AjaxWaitComponent implements DoCheck {
  private hidden = true;
  constructor(private srv: AjaxWaitService) { }
  public get Visible() { return !this.Hidden; }
  public get Hidden() { return this.srv.Hidden; }
  ngDoCheck(): void {
    this.hidden = this.srv.Hidden;
  }
}