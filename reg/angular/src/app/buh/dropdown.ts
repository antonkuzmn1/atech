export class Dropdown {
    constructor(
        public buhpoint: { id: number, name: string }[],
        public info: { id: number, name: string }[],
        public state: { id: number, name: string }[],
    ) { }
}