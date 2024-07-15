import { Component } from '@angular/core';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css'],
})
export class CalculatorComponent {
  display: string = '';

  operandos = ['', ''];
  operator = '';

  addNumber(op: number) {
    if (this.operator === '') {
      this.operandos[0] += op.toString();
      this.display = this.operandos[0];
    } else {
      this.operandos[1] += op.toString();
      this.display += op.toString();
    }
  }

  addComma() {
    if (this.operator === '') {
      if (!this.operandos[0].includes('.')) {
        this.operandos[0] += '.';
        this.display = this.operandos[0];
      }
    } else {
      if (!this.operandos[1].includes('.')) {
        this.operandos[1] += '.';
        this.display += '.';
      }
    }
  }

  addOperator(operator: string) {
    if (this.operator !== '' && this.operandos[1] !== '') {
      this.operandos[0] = this.calculate().toPrecision(15).toString();
      this.operandos[1] = '';
      this.display = this.operandos[0] + operator;
    } else {
      this.display += operator;
    }
    this.operator = operator;
  }

  calculate(): number {
    const num1 = parseFloat(this.operandos[0]);
    const num2 = parseFloat(this.operandos[1]);
    switch (this.operator) {
      case '+':
        return num1 + num2;
      case '-':
        return num1 - num2;
      case '*':
        return num1 * num2;
      case '/':
        return num1 / num2;
      default:
        return NaN;
    }
  }

  calculateButton() {
    this.display = this.calculate().toPrecision(15).toString();
  }

  clear() {
    this.display = '';
    this.operandos[0] = '';
    this.operandos[1] = '';
    this.operator = '';
  }

  toggleSign() {
    if (this.operator === '') {
      if (this.operandos[0] !== '') {
        this.operandos[0] = (parseFloat(this.operandos[0]) * -1).toString();
        this.display = this.operandos[0];
      }
    } else {
      if (this.operandos[1] !== '') {
        this.operandos[1] = (parseFloat(this.operandos[1]) * -1).toString();
        this.display = this.operandos[0] + this.operator + this.operandos[1];
      }
    }
  }

  oneOperatorOperation(operation: string) {
    let num = 0;
    let index = -1;
    if (this.operator === '') {
      num = parseFloat(this.operandos[0]);
      index = 0;
    } else {
      num = parseFloat(this.operandos[1]);
      index = 1;
    }
    switch (operation) {
      case '1/x':
        this.operandos[index] = (1 / num).toString();
        break;
      case 'sqrt':
        this.operandos[index] = Math.sqrt(num).toString();
        break;
      case 'square':
        this.operandos[index] = (num * num).toString();
        break;
      default:
        break;
    }
    if (index == 0) {
      this.display = this.operandos[0];
    } else {
      this.display = this.operandos[0] + this.operator + this.operandos[1];
    }
  }

  delete() {
    if (this.operator === '') {
      this.operandos[0] = this.operandos[0].slice(0, -1);
      this.display = this.operandos[0];
    } else if (this.operandos[1] === '') {
      this.operator = '';
      this.display = this.operandos[0];
    } else {
      this.operandos[1] = this.operandos[1].slice(0, -1);
      this.display = this.operandos[0] + this.operator + this.operandos[1];
    }
  }

  handleKeyPress(event: KeyboardEvent) {
    const key = event.key;

    if (!isNaN(Number(key))) {
      this.addNumber(Number(key));
    } else if (key === '.') {
      this.addComma();
    } else if (key === '+' || key === '-' || key === '*' || key === '/') {
      this.addOperator(key);
    } else if (key === 'Enter') {
      this.calculateButton();
    } else if (key === 'Backspace') {
      this.delete();
    } else if (key === 'Escape') {
      this.clear();
    } else if (key === '%') {
      this.addOperator('%');
    } else if (key === 'x') {
      this.oneOperatorOperation('square');
    } else if (key === 'r') {
      this.oneOperatorOperation('sqrt');
    } else if (key === 'i') {
      this.oneOperatorOperation('1/x');
    }
  }
}