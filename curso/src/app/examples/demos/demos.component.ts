import { Component, OnDestroy, OnInit } from '@angular/core';
import { NotificationService, NotificationType } from '../../common-services';
import { Unsubscribable } from 'rxjs';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MyCoreModule } from '@my/core';

@Component({
  selector: 'app-demos',
  standalone: true,
  imports: [CommonModule, FormsModule, MyCoreModule ],
  templateUrl: './demos.component.html',
  styleUrl: './demos.component.css',
})
export class DemosComponent implements OnInit, OnDestroy {
  private _name: string = 'mundo';
  date = '2024-07-11';
  fontSize = 24;
  provincesList = [
    { id: 1, name: 'Madrid' },
    { id: 2, name: 'BARCELONA' },
    { id: 3, name: 'oviedo' },
    { id: 4, name: 'ciudad Real' },
  ];
  idProvince = 2;

  result?: string;
  visible = true;
  looking = { important: true, error: false, urgent: true };

  private suscriptor: Unsubscribable | undefined;

  constructor(public vm: NotificationService) {}

  public get Name(): string {
    return this._name;
  }
  public set Name(value: string) {
    if (this._name === value) return;
    this._name = value;
  }

  public greet(): void {
    this.result = `Hola ${this.Name}`;
  }

  public goodbye(): void {
    this.result = `Adios ${this.Name}`;
  }

  public say(something: string): void {
    this.result = `Dice ${something}`;
  }

  switchVisibility() {
    this.visible = !this.visible;
    this.looking.error = !this.looking.error;
    this.looking.important = !this.looking.important;
  }

  public calculate(a: number, b: number): number { return a + b }

  public add(province: string) {
    const id = this.provincesList[this.provincesList.length -1].id + 1
    this.provincesList.push({id, name: province})
    this.idProvince = id
  }

  ngOnInit(): void {
    this.suscriptor = this.vm.Notificacion.subscribe((n) => {
      if (n.Type !== NotificationType.error) {
        return;
      }
      window.alert(`Suscripcion: ${n.Message}`);
      this.vm.remove(this.vm.Listado.length - 1);
    });
  }

  ngOnDestroy(): void {
    if (this.suscriptor) {
      this.suscriptor.unsubscribe();
    }
  }
}
