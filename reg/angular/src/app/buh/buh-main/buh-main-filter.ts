export class BuhMainFilter {
    constructor(
        public date: {from: Date, to: Date} | null = null,
        public cagent: number[] = [],
        public dest: string = "",
        public ipay: number[] = [],
        public sum: {from: number, to: number} | null = null,
        public info: number[] = [],
        public buhpoint: number[] = [],
        public state: number[] = [],
        public dcopy: {from: Date, to: Date, null: boolean} | null = null,
        public dorig: {from: Date, to: Date, null: boolean} | null = null,
        public title: string = "",
    ) { }
}