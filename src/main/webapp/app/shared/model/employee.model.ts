export interface IEmployee {
    id?: number;
    name?: string;
    salary?: number;
    commission?: number;
}

export class Employee implements IEmployee {
    constructor(public id?: number, public name?: string, public salary?: number, public commission?: number) {}
}
