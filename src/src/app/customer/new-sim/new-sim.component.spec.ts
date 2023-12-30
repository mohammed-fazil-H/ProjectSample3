import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewSimComponent } from './new-sim.component';

describe('NewSimComponent', () => {
  let component: NewSimComponent;
  let fixture: ComponentFixture<NewSimComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewSimComponent]
    });
    fixture = TestBed.createComponent(NewSimComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
