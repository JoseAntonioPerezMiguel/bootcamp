import { Routes } from '@angular/router';
import { FilmsAddComponent, FilmsEditComponent, FilmsListComponent, FilmsViewComponent } from './films/component.component';

export const routes: Routes = [
    { path: 'films', children: [
      { path: '', component: FilmsListComponent },
      { path: 'add', component: FilmsAddComponent },
      { path: ':id/edit', component: FilmsEditComponent },
      { path: ':id', component: FilmsViewComponent },
    ]}
  ];
