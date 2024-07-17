import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'elipsis',
  standalone: true,
})
export class ElipsisPipe implements PipeTransform {
  transform(value: any, maxlen: number): any {
    return !maxlen || maxlen < 2 || !value || value.length <= maxlen
      ? value
      : value.substr(0, maxlen - 1) + '\u2026';
  }
}

@Pipe({
  name: 'capitalize',
  standalone: true,
})
export class CapitalizePipe implements PipeTransform {
  transform(value: string): any {
    return value
      ?.toString()
      .toLowerCase()
      .split('.')
      .map((frase) => frase.trim())
      .map((frase, index, array) =>
        frase.length === 0
          ? array.length > 1 && index + 1 < array.length
            ? array[index + 1] === ''
              ? '.'
              : '. '
            : ''
          : frase.charAt(0)?.toUpperCase() +
            frase?.substring(1) +
            (array.length > 1 && index + 1 < array.length
              ? array[index + 1] === ''
                ? '.'
                : '. '
              : '')
      )
      .join('')
      .trim();
  }
}

@Pipe({
  name: 'capitalizeAllWords',
  standalone: true,
})
export class CapitalizeAllWordsPipe implements PipeTransform {
  transform(value: string): any {
    return value
      ?.toString()
      .toLowerCase()
      .split(' ')
      .map(word => word.charAt(0).toUpperCase() + word.substring(1))
      .join(' ');
  }
}

@Pipe({
  name: 'errormsg',
  standalone: true,
})
export class ErrorMessagePipe implements PipeTransform {
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  transform(value: any, patternMsg?: string): string {
    if (!value) return '';
    let msg = '';
    for (const err in value) {
      switch (err) {
        case 'required':
          msg += 'It is mandatory. ';
          break;
        case 'minlength':
          msg += `The minimum length must be at least ${value[err].requiredLength} characters. `;
          break;
        case 'maxlength':
          msg += `The maximum length must not exceed ${value[err].requiredLength} characters. `;
          break;
        case 'pattern':
          msg += (patternMsg ? patternMsg : 'Incorrect format') + '. ';
          break;
        case 'email':
          msg += 'The format of the e-mail is not correct. ';
          break;
        case 'min':
          msg += `The value must be greater than or equal to ${value[err].min}. `;
          break;
        case 'max':
          msg += `The value must be less than or equal to ${value[err].max}. `;
          break;
        default:
          if (typeof value[err] === 'string')
            msg += `${value[err]}${value[err].endsWith('.') ? '' : '.'} `;
          else if (typeof value[err]?.message === 'string')
            msg += `${value[err].message}${
              value[err].message.endsWith('.') ? '' : '.'
            } `;
          break;
      }
    }
    return msg.trim();
  }
}

export const PIPES_STRINGS = [ElipsisPipe, CapitalizePipe, ErrorMessagePipe];
