import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

export class BuhMainTable {
  constructor(
    public id: number,
    public inputDate: Date,
    public contractor: { id: number; name: string },
    public destination: string,
    public initiator: { id: number; name: string },
    public sum: number,
    public sumClosing: number,
    public about: { id: number; text: string },
    public mark: { id: number; text: string },
    public status: { id: number; text: string },
    public copyDate: Date,
    public origDate: Date,
    public title: string,

    public copyDate_string: string,
    public origDate_string: string,

    public isChanged_sumClosing: boolean = false,
    public isChanged_about: boolean = false,
    public isChanged_mark: boolean = false,
    public isChanged_status: boolean = false,
    public isChanged_copyDate: boolean = false,
    public isChanged_origDate: boolean = false,
    public isChanged_title: boolean = false,

    public isActive_copyDate: boolean = false,
    public isActive_origDate: boolean = false,

    public ngb_date: NgbDateStruct
  ) {}
}
