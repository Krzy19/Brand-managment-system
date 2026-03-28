export interface SimpleBrand {
  id: string;
  brandName?: string;
}

export interface Ingredient {
  id: string;
  flavor: string;
  sparkling: boolean;
  brand?: SimpleBrand;
  brandId?: string;
  brandName?: string | null;
}
