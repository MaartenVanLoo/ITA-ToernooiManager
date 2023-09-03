export class Competitor{
    id: string;
    weightId: number;
    firstName: string;
    lastName: string;

    birthYear: number = 0;
    belt: number = 0;

    weight: number = -1;

    club: string = "";

    country: string = "";


    static toObject(competitor:Competitor): Competitor{
        return new Competitor(competitor.id, competitor.weightId, competitor.firstName,competitor.lastName, competitor.birthYear, competitor.belt, competitor.weight,competitor.club, competitor.country);
    }
    constructor(id: string, weightId: number, firstName: string, lastName: string, birthYear: number, belt: number, weight: number, club: string, country: string) {
        this.id = id;
        this.weightId = weightId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.belt = belt;
        this.weight = weight;
        this.club = club;
        this.country = country;
    }
}