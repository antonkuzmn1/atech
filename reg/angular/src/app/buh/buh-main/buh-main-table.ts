export class BuhMainTable {
    constructor(
        public id: number,
        public date: Date,
        public cagent: number,
        public dest: string,
        public ipay: number,
        public sum: number,
        public info: number,
        public buhpoint: number,
        public state: number,
        public dcopy: Date | null,
        public dorig: Date | null,
        public title: string, 
    ) {}
}