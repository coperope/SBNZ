-- Customer
INSERT INTO public.customer(id, name, surname, email, password, phone_number)
	VALUES (nextval('users_id_seq'), 'Vladimir', 'Lenjin', 'customer@gmail.com', '123456', '061265978');

-- Categories
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Economy');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Compact');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Intermediate');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Standard');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Full size');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Luxury');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Premium');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Sport');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Coupe');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Passenger van');
INSERT INTO public.category(id, name)
	VALUES (nextval('category_id_seq'), 'Pick up');

-- Tags
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'High fuel consumption');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Medium fuel consumption');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Low fuel consumption');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Sport engine power');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'High engine power');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Medium engine power');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Low engine power');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Family car');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Small family car');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Big family car');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Long range');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Medium range');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Short range');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Unlimited mileage');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'High mileage price');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Medium mileage price');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Low mileage price');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Automatic');
INSERT INTO public.tag(id, name)
	VALUES (nextval('tag_id_seq'), 'Manual');

-- Brand
INSERT INTO public.brand(id, name)
	VALUES (nextval('brand_id_seq'), 'BMW');

-- Car Model
INSERT INTO public.car_model(id, name, brand_id)
	VALUES (nextval('model_id_seq'), 'X5', 1);

-- Fuel
INSERT INTO public.fuel(id, name)
	VALUES (nextval('fuel_id_seq'), 'Diesel');

-- Transmission
INSERT INTO public.transmission(id, name)
	VALUES (nextval('transmission_id_seq'), 'Automatic');

-- Vehicle
INSERT INTO public.vehicle(
	id, ac, door_no, fuel_consumption, mileage_limit, seats_no, brand_id, fuel_id, model_id, transmission_id, cargo_volume, height, length, mileage_price, number_of_reviews, number_of_stars, passenger_area_volume, power, price_per_day, tank_volume, weight, width)
	VALUES (nextval('vehicle_id_seq'), true, 4, 8, 50, 4, 1, 1, 1, 1, 300, 155, 395, -1, 20, 80 , 2700, 80, 4, 40, 1347, 185);

