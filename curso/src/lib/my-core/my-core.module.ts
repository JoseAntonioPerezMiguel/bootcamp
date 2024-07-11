import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PIPES_STRINGS } from './pipes/strings.pipe';



@NgModule({
  declarations: [],
  exports: [ PIPES_STRINGS],
  imports: [
    CommonModule, PIPES_STRINGS
  ]
})
export class MyCoreModule { }
