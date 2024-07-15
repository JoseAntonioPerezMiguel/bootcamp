import { CommonModule, DatePipe } from '@angular/common';
import { Component, forwardRef, OnDestroy, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TypeValidator, ErrorMessagePipe } from '@my/core';
import { PeliculasViewModelService } from './servicios.service';

@Component({
  selector: 'app-peliculas',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.css'],
  standalone: true,
  imports: [
    forwardRef(() => PeliculasAddComponent),
    forwardRef(() => PeliculasEditComponent),
    forwardRef(() => PeliculasViewComponent),
    forwardRef(() => PeliculasListComponent),
  ],
})
export class PeliculasComponent implements OnInit, OnDestroy {
  constructor(protected vm: PeliculasViewModelService) {}
  public get VM(): PeliculasViewModelService {
    return this.vm;
  }
  ngOnInit(): void {
    this.vm.list();
  }
  ngOnDestroy(): void {
    this.vm.clear();
  }
}

@Component({
  selector: 'app-peliculas-list',
  templateUrl: './tmpl-list.component.html',
  imports: [CommonModule],
  styleUrls: ['./componente.component.css'],
  standalone: true,
})
export class PeliculasListComponent implements OnInit, OnDestroy {
  constructor(protected vm: PeliculasViewModelService) {}
  public get VM(): PeliculasViewModelService {
    return this.vm;
  }
  ngOnInit(): void {}
  ngOnDestroy(): void {
    this.vm.clear();
  }
}

@Component({
  selector: 'app-peliculas-add',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css'],
  standalone: true,
  imports: [FormsModule, TypeValidator, ErrorMessagePipe],
})
export class PeliculasAddComponent implements OnInit {
  constructor(protected vm: PeliculasViewModelService) {}
  public get VM(): PeliculasViewModelService {
    return this.vm;
  }
  ngOnInit(): void {}
}
@Component({
  selector: 'app-peliculas-edit',
  templateUrl: './tmpl-form.component.html',
  styleUrls: ['./componente.component.css'],
  standalone: true,
  imports: [FormsModule, TypeValidator, ErrorMessagePipe],
})
export class PeliculasEditComponent implements OnInit, OnDestroy {
  constructor(protected vm: PeliculasViewModelService) {}
  public get VM(): PeliculasViewModelService {
    return this.vm;
  }
  ngOnInit(): void {}
  ngOnDestroy(): void {}
}
@Component({
  selector: 'app-peliculas-view',
  templateUrl: './tmpl-view.component.html',
  styleUrls: ['./componente.component.css'],
  standalone: true,
  imports: [DatePipe],
})
export class PeliculasViewComponent implements OnInit, OnDestroy {
  constructor(protected vm: PeliculasViewModelService) {}
  public get VM(): PeliculasViewModelService {
    return this.vm;
  }
  ngOnInit(): void {}
  ngOnDestroy(): void {}
}

export const PELICULAS_COMPONENTES = [
  PeliculasComponent,
  PeliculasListComponent,
  PeliculasAddComponent,
  PeliculasEditComponent,
  PeliculasViewComponent,
];
