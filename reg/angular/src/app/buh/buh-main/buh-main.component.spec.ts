import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuhMainComponent } from './buh-main.component';

describe('BuhMainComponent', () => {
  let component: BuhMainComponent;
  let fixture: ComponentFixture<BuhMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BuhMainComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BuhMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
