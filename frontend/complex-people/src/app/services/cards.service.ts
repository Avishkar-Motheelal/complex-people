import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {map, Observable} from "rxjs";
import {AccessCard} from "../models/access-card.model";
import {AccesscardHelper} from "../helper/accesscard-helper";

@Injectable({
  providedIn: 'root'
})
export class CardsService {

  constructor(private http: HttpClient) {
  }

  getCardForPerson(peopleId: Number): Observable<AccessCard[]> {
    return this.http.get<any[]>(`${environment.apiUrl}/people/${peopleId}/accesscards`)
      .pipe(
        map(cards => cards.map(card => AccesscardHelper.createAccesscard(card)))
      );
  }

  addCardForPerson(peopleId: Number) {
    return this.http.post(`${environment.apiUrl}/people/${peopleId}/accesscard`, {});
  }

  disableCard(accesscardId: String) {
    return this.http.delete(`${environment.apiUrl}/people/accesscard`, {body: JSON.stringify({accessCardId: accesscardId})});
  }
}
