import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuhAllComponent } from './buh-all.component';

describe('BuhAllComponent', () => {
  let component: BuhAllComponent;
  let fixture: ComponentFixture<BuhAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BuhAllComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BuhAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
