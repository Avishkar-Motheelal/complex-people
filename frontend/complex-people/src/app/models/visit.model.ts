import {Person} from "./person.model";
import {Apartment} from "./apartment.model";
import {Photo} from "./photo";

export interface Visit {
  visitorId: number;
  visitor: Person;
  apartment: Apartment;
  dateIn: Date;
  dateOut: Date;
  photo: Photo;
}
