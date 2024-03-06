import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateAppliedLoanComponent } from './update-applied-loan.component';

describe('UpdateAppliedLoanComponent', () => {
  let component: UpdateAppliedLoanComponent;
  let fixture: ComponentFixture<UpdateAppliedLoanComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateAppliedLoanComponent]
    });
    fixture = TestBed.createComponent(UpdateAppliedLoanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
