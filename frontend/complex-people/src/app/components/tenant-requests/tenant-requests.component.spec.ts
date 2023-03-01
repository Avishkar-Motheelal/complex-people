import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TenantRequestsComponent } from './tenant-requests.component';

describe('TenantRequestsComponent', () => {
  let component: TenantRequestsComponent;
  let fixture: ComponentFixture<TenantRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TenantRequestsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TenantRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
