import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {IssuesModel} from "../models/issues_model";

@Injectable({
  providedIn: 'root'
})
export class TheserviceService {

  myMethod$: Observable<any>;
  private myMethodSubject = new Subject<any>();

  issueMethod$: Observable<IssuesModel>;
  private myIssueSubject = new Subject<IssuesModel>();

  constructor() {
    this.myMethod$ = this.myMethodSubject.asObservable();
    this.issueMethod$ = this.myIssueSubject.asObservable();
  }

  myMethod(data: any) {
    console.log(data); // I have data! Let's return it so subscribers can use it!
    // we can do stuff with data if we want
    this.myMethodSubject.next(data);
  }

  issueMethod(data: any) {
    console.log(data);
    this.myIssueSubject.next(data);
  }
}
