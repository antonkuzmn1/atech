export class BuhDropdown {
    constructor(
        public buhpoint: { id: number, name: string }[],
        public info: { id: number, name: string }[],
        public state: { id: number, name: string }[],
    ) { }
}
