import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PIPES_STRINGS } from './pipes/strings.pipe';
import { SizerComponent } from './components/sizer.component';
import GraficoSvgComponent from './components/grafico-svg/grafico-svg.component';



@NgModule({
  declarations: [],
  exports: [ PIPES_STRINGS, SizerComponent, GraficoSvgComponent, ],
  imports: [
    CommonModule, PIPES_STRINGS, SizerComponent, GraficoSvgComponent,
  ]
})
export class MyCoreModule { }
