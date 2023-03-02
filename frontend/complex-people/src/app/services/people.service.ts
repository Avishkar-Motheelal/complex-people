import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map, Observable} from "rxjs";
import {Person} from "../models/person.model";
import {environment} from "../../environments/environment";
import {AddPerson} from "../models/addPerson";
import {PersonHelper} from "../helper/person-helper";
import {NewResidentDto} from "../models/new-resident-dto.model";

@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  constructor(private http: HttpClient) {
  }

  private peopleUrl = `${environment.apiUrl}/people`;

  getPeople(): Observable<Person[]> {
    return this.http.get<any[]>(this.peopleUrl).pipe(
      map(people => people.map(person => {
        return PersonHelper.createPerson(person);
      })),
    );
  }

  getPerson(id: number) {
    const personUrl = `${this.peopleUrl}/${id}`;
    return this.http.get<any>(personUrl).pipe(
      map(person => {
        return PersonHelper.createPerson(person);
      })
    )
  };

  addNewPerson(newResidentDto: NewResidentDto) {
    return this.http.post(this.peopleUrl, newResidentDto);
  }

  updatePerson(person: AddPerson, id: number) {
    return this.http.patch(`${environment.apiUrl}/people/${id}`, person, {observe: 'response'});
  }
}
