import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {IssuesModel} from "../models/issues_model";

@Injectable({
  providedIn: 'root'
})
export class HidecomponentService {

  myMethod$: Observable<any>;
  private myMethodSubject = new Subject<any>();

  issueMethod$: Observable<IssuesModel>;
  private myIssueSubject = new Subject<IssuesModel>();

  constructor() {
    this.myMethod$ = this.myMethodSubject.asObservable();
    this.issueMethod$ = this.myIssueSubject.asObservable();
  }

  myMethod(data: any) {
    this.myMethodSubject.next(data);
  }

  issueMethod(data: any) {
    this.myIssueSubject.next(data);
  }
}
