import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {map} from "rxjs";
import {Visit} from "../models/visit.model";
import {Person} from "../models/person.model";
import {PersonHelper} from "../helper/person-helper";
import {Apartment} from "../models/apartment.model";

@Injectable({
  providedIn: 'root'
})
export class VisitorsService {

  constructor(private Http: HttpClient) { }

  private visitUrl = `${environment.apiUrl}/visit`;
  getVisitors() {
    return this.Http.get<any[]>(this.visitUrl).pipe(
      map(visits => visits.map(visit => {

        return new class implements Visit{
          apartment: Apartment = visit.apartment;
          dateIn: Date = visit.dateIn;
          dateOut: Date = visit.dateOut;
          photo: URL = visit.photo.photoURL;
          visitor: Person = PersonHelper.createPerson(visit.visitor);
          visitorId = visit.visitorId
        }
      }))
    )

  }
}
