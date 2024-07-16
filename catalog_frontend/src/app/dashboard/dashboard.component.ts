import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FilmsComponent } from '../films';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FilmsComponent, RouterModule, ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  menu = [
    {text: 'Films', icon: '', component: FilmsComponent},
  ]

  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  actual: any = this.menu[0].component

  select(index: number) {
    this.actual = this.menu[index].component
  }

}
