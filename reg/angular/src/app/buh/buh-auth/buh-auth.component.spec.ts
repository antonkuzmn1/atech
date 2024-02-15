import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuhAuthComponent } from './buh-auth.component';

describe('BuhAuthComponent', () => {
  let component: BuhAuthComponent;
  let fixture: ComponentFixture<BuhAuthComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BuhAuthComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BuhAuthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
