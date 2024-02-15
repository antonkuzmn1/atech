import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuhImportComponent } from './buh-import.component';

describe('BuhImportComponent', () => {
  let component: BuhImportComponent;
  let fixture: ComponentFixture<BuhImportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BuhImportComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BuhImportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
