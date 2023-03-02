import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IssuesModel} from "../models/issues_model";
import {IssueHelper} from "../helper/issue-help";

@Injectable({
  providedIn: 'root'
})
export class IssuesService {


  issuesUrl: string = 'http://localhost:8080/issues';

  constructor(private http: HttpClient) { }

  getAllIssues(): Observable<IssuesModel[]> {
    return this.http.get<IssuesModel[]>(this.issuesUrl);
  }

  saveNewIssue(issue: IssueHelper) {
    issue.personId = 1;
    return this.http.post(this.issuesUrl, issue);
  }

}
