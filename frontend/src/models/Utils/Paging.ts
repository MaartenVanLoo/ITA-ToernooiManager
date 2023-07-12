export class Pageable{
  offset: number;
  pageSize: number;
  pageNumber: number;
  paged: boolean;
  unpaged: boolean;
  sort : {
    "empty":boolean;
    "sorted":boolean;
    "unsorted":boolean;
  }


  constructor(offset: number, pageSize: number, pageNumber: number, paged: boolean, unpaged: boolean, sort: { empty: boolean; sorted: boolean; unsorted: boolean }) {
    this.offset = offset;
    this.pageSize = pageSize;
    this.pageNumber = pageNumber;
    this.paged = paged;
    this.unpaged = unpaged;
    this.sort = sort;
  }
}
export class Page<T> {
  content: Array<T>;
  //pageable:Pageable;
  totalPages :number;
  totalElements : number;
  size:number;
  number:number;
  first:boolean
  numberOfElements:number
  empty:boolean;
  sort : {
    empty:boolean;
    sorted:boolean;
    unsorted:boolean;
  }

  //constructor(content: Array<T>, pageable: Pageable, totalPages: number, totalElements: number, size: number, number: number, first: boolean, numberOfElements: number, empty: boolean, sort: { empty: boolean; sorted: boolean; unsorted: boolean }) {
  constructor(content: Array<T>, totalPages: number, totalElements: number, size: number, number: number, first: boolean, numberOfElements: number, empty: boolean, sort: { empty: boolean; sorted: boolean; unsorted: boolean }) {
    this.content = content;
    //this.pageable = pageable;
    this.totalPages = totalPages;
    this.totalElements = totalElements;
    this.size = size;
    this.number = number;
    this.first = first;
    this.numberOfElements = numberOfElements;
    this.empty = empty;
    this.sort = sort;
  }
}


//Sortin
export const Direction = {
  asc: "asc",
  desc: "desc"
}
export class Sortable{
  key :string ="";
  direction: string = Direction.asc;

  constructor(key: string, direction: string) {
    this.key = key;
    this.direction = direction;
  }

  toggle(field:string){
    if (this.key.includes(field)){
      this.direction = this.direction===Direction.asc?Direction.desc:Direction.asc
      return;
    }
    this.key = field;
  }
  getSortIcon(field:string){
    if (this.key.includes(field)){
      return this.direction===Direction.asc?'mdi-arrow-down-thin':'mdi-arrow-up-thin'
    }
    return "";
  }
}


