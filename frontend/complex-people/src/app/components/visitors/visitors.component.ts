import { Component } from '@angular/core';
import {VisitorsService} from "../../services/visitors.service";
import {Visit} from "../../models/visit.model";

@Component({
  selector: 'app-visitors',
  templateUrl: './visitors.component.html',
  styleUrls: ['./visitors.component.css']
})
export class VisitorsComponent {
  visitors: Visit[] = []

  constructor(private visitorsService: VisitorsService) {}

  ngOnInit(): void {
    this.getVisitor();
  }

  getVisitor(): void {
    this.visitorsService.getVisitors().subscribe((visitors) => {
      console.log(visitors);  //Remember to remove this
      this.visitors = visitors;
    })
  }

}
