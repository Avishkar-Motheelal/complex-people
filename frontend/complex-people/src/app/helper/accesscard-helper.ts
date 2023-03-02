import {AccessCard} from "../models/access-card.model";

export class AccesscardHelper {
  static createAccesscard(card: any): AccessCard {
    return new class implements AccessCard {
      accessCardId: string = card.accessCardId;
      activated: boolean = card.activated;
    }
  }
}
