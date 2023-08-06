import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdviserViewComponent } from './adviser-view.component';

describe('AdviserViewComponent', () => {
  let component: AdviserViewComponent;
  let fixture: ComponentFixture<AdviserViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdviserViewComponent]
    });
    fixture = TestBed.createComponent(AdviserViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
