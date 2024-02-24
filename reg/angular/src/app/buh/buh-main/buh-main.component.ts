import { DatePipe, JsonPipe, NgClass, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgxMaskDirective } from 'ngx-mask';
import { BuhDropdownList } from '../classes/buh-dropdown-list';
import { BuhEntityIdNameBool } from '../classes/buh-entity-id-name-bool';
import { BuhMainService } from './buh-main.service';
import { BuhMainFilter } from './classes/buh-main-filter';
import { BuhMainTable } from './classes/buh-main-table';

@Component({
  selector: 'app-buh-main',
  standalone: true,
  imports: [
    NgFor,
    NgIf,
    FormsModule,
    NgClass,
    NgxMaskDirective,
    JsonPipe,
    DatePipe,
  ],
  templateUrl: 'buh-main.component.html',
  styleUrl: '../../../styles/components/buh-main.scss',
})
export class BuhMainComponent implements OnInit {
  constructor(private service: BuhMainService, public filter: BuhMainFilter) {}

  table: BuhMainTable[] = [];
  editedRows: BuhMainTable[] = [];
  dd: BuhDropdownList = new BuhDropdownList();

  contractors: BuhEntityIdNameBool[] = [];
  initiators: BuhEntityIdNameBool[] = [];
  abouts: BuhEntityIdNameBool[] = [];
  marks: BuhEntityIdNameBool[] = [];
  statuses: BuhEntityIdNameBool[] = [];

  isChanged: boolean = false;
  filterIsOpened: boolean[] = [
    false,
    false,
    false,
    false,
    false,
    true,
    false,
    false,
    false,
    false,
    false,
    false,
  ];

  ngOnInit(): void {
    this.tableUpdate();
    this.getDropdowns();
    this.getContractors();
    this.getInitiators();
  }
  getDropdowns(): void {
    this.service.getDropdowns().subscribe({
      next: (data: BuhDropdownList) => {
        this.dd = data;
        this.abouts = [];
        data.about.map((item) => {
          if (this.contractorIsChecked(item.id))
            this.abouts.push({ id: item.id, name: item.text, isChecked: true });
          else
            this.abouts.push({
              id: item.id,
              name: item.text,
              isChecked: false,
            });
        });
        this.marks = [];
        data.mark.map((item) => {
          if (this.contractorIsChecked(item.id))
            this.marks.push({ id: item.id, name: item.text, isChecked: true });
          else
            this.marks.push({ id: item.id, name: item.text, isChecked: false });
        });
        this.statuses = [];
        data.status.map((item) => {
          if (this.contractorIsChecked(item.id))
            this.statuses.push({
              id: item.id,
              name: item.text,
              isChecked: true,
            });
          else
            this.statuses.push({
              id: item.id,
              name: item.text,
              isChecked: false,
            });
        });
      },
    });
  }
  getContractors(): void {
    this.service.getContractors().subscribe({
      next: (data: { id: number; name: string }[]) => {
        this.contractors = [];
        data.map((item) => {
          if (this.contractorIsChecked(item.id))
            this.contractors.push({
              id: item.id,
              name: item.name,
              isChecked: true,
            });
          else
            this.contractors.push({
              id: item.id,
              name: item.name,
              isChecked: false,
            });
        });
        this.contractors.sort((a, b) => {
          if (a.name < b.name) return -1;
          if (a.name > b.name) return 1;
          return 0;
        });
      },
    });
  }
  getInitiators(): void {
    this.service.getInitiators().subscribe({
      next: (data: { id: number; name: string }[]) => {
        this.initiators = [];
        data.map((item) => {
          if (this.initiatorIsChecked(item.id))
            this.initiators.push({
              id: item.id,
              name: item.name,
              isChecked: true,
            });
          else
            this.initiators.push({
              id: item.id,
              name: item.name,
              isChecked: false,
            });
        });
        this.initiators.sort((a, b) => {
          if (a.name < b.name) return -1;
          if (a.name > b.name) return 1;
          return 0;
        });
      },
    });
  }
  tableUpdate(): void {
    this.service.get().subscribe({
      next: (data: BuhMainTable[]) => {
        this.table = data.map((row) => {
          if (row.copyDate)
            row.copyDate_string = row.copyDate.toString().split('T')[0];
          if (row.origDate)
            row.origDate_string = row.origDate.toString().split('T')[0];
          return row;
        });
      },
    });
  }

  control(row: BuhMainTable): number {
    switch (row.mark.id) {
      case 0:
        return 3;
      case 1:
        return 1;
      case 2:
        return 2;
      case 3:
        return 3;
      default:
        return 3;
    }
  }

  onChange(row: BuhMainTable, key: string, value: any = null): void {
    switch (key) {
      case 'sumClosing':
        row.isChanged_sumClosing = true;
        break;

      case 'about':
        row.isChanged_about = true;
        row.about = this.dd.about[value.value];
        break;

      case 'mark':
        row.isChanged_mark = true;
        row.mark = this.dd.mark[value.value];
        break;

      case 'status':
        row.isChanged_status = true;
        row.status = this.dd.status[value.value];
        break;

      case 'copyDate':
        row.copyDate = new Date(row.copyDate_string);
        row.isChanged_copyDate = true;
        break;

      case 'origDate':
        row.origDate = new Date(row.origDate_string);
        row.isChanged_origDate = true;
        break;

      case 'title':
        row.isChanged_title = true;
        break;
    }
    this.isChanged = true;
    const id = row.id;
    this.editedRows = this.editedRows.filter((row) => row.id !== id);
    this.editedRows.push(row);
  }

  allow(): boolean {
    return this.service.allow();
  }

  isExistsInEditedRows(id: number): boolean {
    for (const row of this.editedRows) if (row.id === id) return true;
    return false;
  }

  submit(): void {
    this.service.edit(this.editedRows).subscribe({
      next: (data: boolean) => {
        if (data) {
          this.editedRows = [];
          this.isChanged = false;
          this.tableUpdate();
        }
      },
    });
  }

  filterToggle(key: number) {
    this.filterIsOpened.forEach((value, index) => {
      if (index !== key) this.filterIsOpened[index] = false;
    });
    this.filterIsOpened[key] = !this.filterIsOpened[key];
  }

  toggleCheckbox(element: BuhEntityIdNameBool) {
    const checkbox = document.getElementById(element.id.toString()) as HTMLInputElement;
    element.isChecked = !element.isChecked;
    checkbox.checked = !checkbox.checked;
  }

  contractorFilterConfirm() {
    this.filter.contractor = [];
    this.contractors.map((item) => {
      if (item.isChecked) this.filter.contractor.push(item.id);
    });
    this.tableUpdate();
  }

  contractorFilterReset() {
    this.filter.contractor = [];
    this.contractors.forEach((element) => (element.isChecked = false));
    console.log(this.contractors);
  }

  contractorIsChecked(id: number) {
    return this.filter.contractor.includes(id);
  }

  initiatorFilterConfirm() {
    this.filter.initiator = [];
    this.initiators.map((item) => {
      if (item.isChecked) this.filter.initiator.push(item.id);
    });
    this.tableUpdate();
  }

  initiatorFilterReset() {
    this.filter.initiator = [];
    this.initiators.forEach((element) => (element.isChecked = false));
    console.log(this.initiators);
  }

  initiatorIsChecked(id: number) {
    return this.filter.initiator.includes(id);
  }

  ddFilterManager = {
    confirm: (name: string): void => {
      switch (name) {
        case 'about':
          this.filter.about = [];
          this.abouts.map((item) => {
            if (item.isChecked) this.filter.about.push(item.id);
          });
          break;
        case 'mark':
          this.filter.about = [];
          this.abouts.map((item) => {
            if (item.isChecked) this.filter.about.push(item.id);
          });
      }
      this.tableUpdate();
    },
    reset: (name: string): void => {
      switch (name) {
        case 'about':
          this.filter.about = [];
          this.abouts.forEach((element) => (element.isChecked = false));
          break;
        case 'mark':
          this.filter.mark = [];
          this.marks.forEach((element) => (element.isChecked = false));
          break;
        case 'status':
          this.filter.status = [];
          this.statuses.forEach((element) => (element.isChecked = false));
          break;
        default:
          throw new Error('o kurwa!');
      }
    },
    check: (name: string, id: number): boolean => {
      switch (name) {
        case 'about':
          return this.filter.about.includes(id);
        case 'mark':
          return this.filter.mark.includes(id);
        case 'status':
          return this.filter.mark.includes(id);
        default:
          throw new Error('o kurwa!');
      }
    },
  };
}
