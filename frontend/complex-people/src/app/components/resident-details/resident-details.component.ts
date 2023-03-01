import {Component, Input} from '@angular/core';
import {Person} from "../../models/person.model";
import {ActivatedRoute} from "@angular/router";
import {PeopleService} from "../../services/people.service";
import {AccessCard} from "../../models/access-card.model";
import {CardsService} from "../../services/cards.service";


@Component({
  selector: 'app-resident-details',
  templateUrl: './resident-details.component.html',
  styleUrls: ['./resident-details.component.css']
})
export class ResidentDetailsComponent {
  @Input() person?: Person;
  @Input() cards?: AccessCard[];

  constructor(
    private route: ActivatedRoute,
    private peopleService: PeopleService,
    private cardsService: CardsService,
  ) {}


  ngOnInit(): void {
    this.getPerson();
    this.getCards();
  }

  getPerson(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.peopleService.getPerson(id)
      .subscribe(person => this.person = person);
  }

  getCards() {
    this.cards = [
      {
        accessCardId: '37d88e22-8f42-4f01-a37d-671a7086f454',
        activated: false,
      },
      {
        accessCardId: 'a4b63a63-79ec-4c19-9124-cfda4d4c0a4b',
        activated: true,
      },
    ];

    // for actual push
    const id = this.route.snapshot.paramMap.get('id');
    this.cardsService.getCardForPerson(id);
  }

}
