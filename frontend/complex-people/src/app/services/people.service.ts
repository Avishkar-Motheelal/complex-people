import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map, Observable} from "rxjs";
import {Person} from "../models/person.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  constructor(private http: HttpClient) {}

  private peopleUrl = `${environment.apiUrl}/people`;

  getPeople(): Observable<Person[]> {
    return this.http.get<any[]>(this.peopleUrl).pipe(
      map(people => people.map(person => {
        return PeopleService.createPerson(person);
      })),
    );
}

  getPerson(id: number) {
    const personUrl = `${this.peopleUrl}/${id}`;
    return this.http.get<any>(personUrl).pipe(
      map(person => {
        console.log(person);
        return PeopleService.createPerson(person);
      })
    )
  };

  public static createPerson(person: any): Person {
    return new class implements Person {
      emailAddress: string = person.contactDetail.emailAddress;
      firstName: string = person.firstName;
      identificationDocumentNumber: string = person.identificationDocument.number;
      identificationDocumentType: string = person.identificationDocument.documentType.type;
      lastName: string = person.lastName;
      peopleId: number = person.peopleId;
      phoneNumber: string = person.contactDetail.phoneNumber;
      roles: string[] = person.roles.map((role: { type: any; }) => role.type);
    }
  }
}
