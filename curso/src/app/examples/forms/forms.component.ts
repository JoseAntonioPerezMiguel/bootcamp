import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ErrorMessagePipe, TypeValidator, UppercaseValidator } from '@my/core';

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

  add() {
    this.element = {};
    this.mode = 'add';
  }

  edit(key: number) {
    this.element = {
      id: key,
      name: 'Pepito',
      lastname: 'Grillo',
      email: 'pgrillo@example.com',
      age: 99,
      date: '2024/07/12',
      conflictive: true,
    };
    this.mode = 'edit';
  }

  cancel() {}

  send() {
    switch (this.mode) {
      case 'add':
        window.alert(`POST: ${JSON.stringify(this.element)}`)
        this.cancel()
        break
      case 'edit':
        window.alert(`POST: ${JSON.stringify(this.element)}`)
        this.cancel()
        break
    }
  }
}
