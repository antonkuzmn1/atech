export class BuhAllTable {
    constructor(
        public id: number,
        public inputDate: Date,
        public contractor: { id: number, name: string },
        public initiator: { id: number, name: string },
        public destination: string,
        public sum: number,
        public copyDate: Date,
        public origDate: Date,
        public title: string,
        public date: Date,
        public number: number,
        public mark: {id: number, text: string},
        public status: {id: number, text: string},
    ) {}
}