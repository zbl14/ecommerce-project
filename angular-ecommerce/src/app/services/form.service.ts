import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FormService {
  constructor() {}

  getCreditCardMonths(): Observable<number[]> {
    let data: number[] = [];

    for (let theMonth = 1; theMonth <= 12; theMonth++) {
      data.push(theMonth);
    }

    // the "of" operator from rxjs, will wrap an object as an Observable
    // the reason to use it is ng component will subscribe the method to retrieve the data
    return of(data);
  }

  getCreditCardYears(): Observable<number[]> {
    let data: number[] = [];

    const startYear: number = new Date().getFullYear();
    const endYear: number = startYear + 10;

    for (let theYear = startYear; theYear <= endYear; theYear++) {
      data.push(theYear);
    }

    return of(data);
  }
}