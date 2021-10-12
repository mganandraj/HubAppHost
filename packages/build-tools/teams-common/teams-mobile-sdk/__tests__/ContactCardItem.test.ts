import { ContactCardHeroItem, ContactCardItem, ContactCardItemTemplate, ContactCardMultiValueItem, ContactCardSingleValueItem } from '../src/lib/models/contact/ContactCardItem';

it('should return the correct templates', () => {
  let hero = new ContactCardHeroItem();
  expect(hero.getTemplate()).toBe(ContactCardItemTemplate.Hero);

  let singleValue = new ContactCardSingleValueItem();
  expect(singleValue.getTemplate()).toBe(ContactCardItemTemplate.SingleValue);

  let multiValue = new ContactCardMultiValueItem();
  expect(multiValue.getTemplate()).toBe(ContactCardItemTemplate.MultiValue);
});
