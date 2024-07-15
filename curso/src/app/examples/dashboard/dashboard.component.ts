import { Component } from '@angular/core';
import { HomeComponent } from 'src/app/main';
import { DemosComponent } from '../demos/demos.component';
import GraficoSvgComponent from 'src/lib/my-core/components/grafico-svg/grafico-svg.component';
import { NotificationComponent } from "../../main/notification/notification.component";
import { CommonModule } from '@angular/common';
import { CalculatorComponent } from 'src/lib/my-core/components/calculator/calculator.component';
import { FormsComponent } from '../forms/forms.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NotificationComponent, CommonModule, ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  menu = [
    {text: 'Home', icon: '', component: HomeComponent},
    {text: 'Demos', icon: '', component: DemosComponent},
    {text: 'Graphic', icon: '', component: GraficoSvgComponent},
    {text: 'Calculator', icon: '', component: CalculatorComponent},
    {text: 'Forms', icon: '', component: FormsComponent},
  ]

  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  actual: any = this.menu[0].component

  select(index: number) {
    this.actual = this.menu[index].component
  }

}
