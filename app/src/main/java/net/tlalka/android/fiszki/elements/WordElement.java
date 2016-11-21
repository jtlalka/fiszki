package net.tlalka.android.fiszki.elements;

public enum WordElement {

	//colors
	COLORS_BLUE(LessonElement.COLORS.getName(), "blue", "niebieski"), //
	COLORS_RED(LessonElement.COLORS.getName(), "red", "czerwony"), //
	COLORS_BLACK(LessonElement.COLORS.getName(), "black", "czarny"), //
	COLORS_WHITE(LessonElement.COLORS.getName(), "white", "bialy"), //
	COLORS_PINK(LessonElement.COLORS.getName(), "pink", "różowy"), //
	COLORS_YELLOW(LessonElement.COLORS.getName(), "yellow", "żółty"), //
	COLORS_PURPLE(LessonElement.COLORS.getName(), "purple", "fiotelowy"), //
	COLORS_BROWN(LessonElement.COLORS.getName(), "brown", "brązowy"), //
	COLORS_GREEN(LessonElement.COLORS.getName(), "green", "zielony"), //
	COLORS_SILVER(LessonElement.COLORS.getName(), "silver", "srebrny"), //
	COLORS_GOLD(LessonElement.COLORS.getName(), "gold", "złoty"), //
	COLORS_ORANGE(LessonElement.COLORS.getName(), "orange", "pomarańczowy"), //
	
	//animals
	ANIMALS_DOG(LessonElement.ANIMALS.getName(), "dog", "pies"), //
	ANIMALS_CAT(LessonElement.ANIMALS.getName(), "cat", "kot"), //
	ANIMALS_COW(LessonElement.ANIMALS.getName(), "cow", "krowa"), //
	ANIMALS_TURTLE(LessonElement.ANIMALS.getName(), "turtle", "żółw"), //
	ANIMALS_SNAKE(LessonElement.ANIMALS.getName(), "snake", "wąż"), //
	ANIMALS_HAMSTER(LessonElement.ANIMALS.getName(), "hamster", "pies"), //
	ANIMALS_HORSE(LessonElement.ANIMALS.getName(), "horse", "koń"), //
	ANIMALS_DONKEY(LessonElement.ANIMALS.getName(), "donkey", "osioł"), //
	ANIMALS_GOAT(LessonElement.ANIMALS.getName(), "goat", "koza"), //
	ANIMALS_FISH(LessonElement.ANIMALS.getName(), "fish", "ryba"), //
	ANIMALS_TIGER(LessonElement.ANIMALS.getName(), "tiger", "tygrys"), //
	ANIMALS_LION(LessonElement.ANIMALS.getName(), "lion", "lew"), //
	ANIMALS_ZEBRA(LessonElement.ANIMALS.getName(), "zebra", "zebra"), //
	ANIMALS_FROG(LessonElement.ANIMALS.getName(), "frog", "żaba"), //
	ANIMALS_PIG(LessonElement.ANIMALS.getName(), "pig", "świnia"), //
	ANIMALS_MOUSE(LessonElement.ANIMALS.getName(), "mouse", "mysz"), //
	ANIMALS_GUINEA_PIG(LessonElement.ANIMALS.getName(), "guinea pig", "świnka morska"), //
	ANIMALS_KANGAROO(LessonElement.ANIMALS.getName(), "kangaroo", "kangur"), //
	ANIMALS_ELEPHANT(LessonElement.ANIMALS.getName(), "elephant", "słoń"), //
	
	//fruits
	FRUITS_APPLE(LessonElement.FRUITS.getName(), "apple", "jabłko"), //
	FRUITS_BANANA(LessonElement.FRUITS.getName(), "banana", "banan"), //
	FRUITS_KIWI(LessonElement.FRUITS.getName(), "kiwi", "kiwi"), //
	FRUITS_ORANGE(LessonElement.FRUITS.getName(), "orange", "pomarańcza"), //
	FRUITS_GRAPE(LessonElement.FRUITS.getName(), "grape", "winogrono"), //
	FRUITS_STRAWBERREY(LessonElement.FRUITS.getName(), "strawberry", "truskawka"), //
	FRUITS_BLACKBERRY(LessonElement.FRUITS.getName(), "blackberry", "jeżyna"), //
	FRUITS_PINEAPPLE(LessonElement.FRUITS.getName(), "pineapple", "ananas"), //
	FRUITS_PEACH(LessonElement.FRUITS.getName(), "peach", "brzoskwinia"), //
	FRUITS_COCONUT(LessonElement.FRUITS.getName(), "coconut", "kokos"), //
	FRUITS_RASPBERRY(LessonElement.FRUITS.getName(), "raspberry", "malina"), //
	FRUITS_BERRY(LessonElement.FRUITS.getName(), "berry", "jagoda"), //
	FRUITS_PEAR(LessonElement.FRUITS.getName(), "pear", "gruszka"), //
	FRUITS_GRAPEFRUIT(LessonElement.FRUITS.getName(), "grapefruit", "grejpfrut"), //
	FRUITS_PLUM(LessonElement.FRUITS.getName(), "plum", "śliwka"), //
	FRUITS_MANGO(LessonElement.FRUITS.getName(), "mango", "mango"), //
	FRUITS_GOOSEBERRY(LessonElement.FRUITS.getName(), "gooseberry", "agrest"), //
	FRUITS_CHERRY(LessonElement.FRUITS.getName(), "cherry", "wiśnia"), //
	FRUITS_BILBERRY(LessonElement.FRUITS.getName(), "borówka", "bilberry"), //
	FRUITS_WATERMELON(LessonElement.FRUITS.getName(), "watermelon", "arbuz"), //
	
	//sport
	SPORT_FOOTBALL(LessonElement.SPORT.getName(), "football", "piłka nożna"), //
	SPORT_BASKETBALL(LessonElement.SPORT.getName(), "basketball", "koszykówka"), //
	SPORT_TENNIS(LessonElement.SPORT.getName(), "tennis", "tenis"), //
	SPORT_SKIING(LessonElement.SPORT.getName(), "skiing", "narciarstwo"), //
	SPORT_SWIMMING(LessonElement.SPORT.getName(), "swimming", "pływanie"), //
	SPORT_SKATING(LessonElement.SPORT.getName(), "skating", "łyżwiarstwo"), //
	SPORT_RUGBY(LessonElement.SPORT.getName(), "rugby", "rugby"), //
	SPORT_VOLLEYBALL(LessonElement.SPORT.getName(), "volleyball", "siatkówka"), //
	SPORT_CYCLING(LessonElement.SPORT.getName(), "cycling", "kolarstwo"), //
	SPORT_WRESTLING(LessonElement.SPORT.getName(), "wrestling", "zapasy"), //
	SPORT_HORSE_RIDING(LessonElement.SPORT.getName(), "horse riding", "jazda konna"), //
	SPORT_SURFING(LessonElement.SPORT.getName(), "surfing", "surfing"), //
	SPORT_WINDSURFING(LessonElement.SPORT.getName(), "windsurfing", "windsurfing"), //
	SPORT_ICE_HOCKEY(LessonElement.SPORT.getName(), "ice hockey", "hokej na lodzie"), //
	SPORT_GOLF(LessonElement.SPORT.getName(), "golf", "golf"), //
	SPORT_BOWLING(LessonElement.SPORT.getName(), "bowling", "kręgle"), //
	SPORT_BOXING(LessonElement.SPORT.getName(), "boxing", "boks"), //
	SPORT_SNORKELLING(LessonElement.SPORT.getName(), "snorkelling", "nurkowanie"), //
	SPORT_RUNNING(LessonElement.SPORT.getName(), "running", "bieganie"), //
	SPORT_DANCING(LessonElement.SPORT.getName(), "dancing", "taniec"), //
	SPORT_HIGH_JUMPING(LessonElement.SPORT.getName(), "high jump", "skok wzwyż"), //
	SPORT_TABLE_TENNIS(LessonElement.SPORT.getName(), "table tennis", "tenis stołowy"), //
	
	//work
	WORK_JOB(LessonElement.WORK.getName(), "job", "praca"), //
	WORK_PLUMBER(LessonElement.WORK.getName(), "plumber", "hydraulik"), //
	WORK_CASHIER(LessonElement.WORK.getName(), "cashier", "kasjer"), //
	WORK_FIREFIGHTER(LessonElement.WORK.getName(), "firefighter", "strażak"), //
	WORK_DOCTOR(LessonElement.WORK.getName(), "doctor", "lekarz"), //
	WORK_NURSE(LessonElement.WORK.getName(), "nurse", "pielęgniarka"), //
	WORK_FLORIST(LessonElement.WORK.getName(), "florist", "kwiaciarka"), //
	WORK_COOK(LessonElement.WORK.getName(), "cook", "kucharka"), //
	WORK_TAILOR(LessonElement.WORK.getName(), "tailor", "krawiec"), //
	WORK_LAWYER(LessonElement.WORK.getName(), "lawyer", "prawnik"), //
	WORK_SCIENTIST(LessonElement.WORK.getName(), "scientist", "naukowiec"), //
	WORK_TEACHER(LessonElement.WORK.getName(), "teacher", "nauczyciel"), //
	WORK_SECRETARY(LessonElement.WORK.getName(), "secretary", "sekretarka"), //
	WORK_FULL_TIME(LessonElement.WORK.getName(), "full-time", "pełny etat"), //
	WORK_PART_TIME(LessonElement.WORK.getName(), "part-time", "niepełny etat"), //
	WORK_QUALIFICATIONS(LessonElement.WORK.getName(), "qualifications", "kwalifikacje"), //
	WORK_FLEXIBLE_HOURS(LessonElement.WORK.getName(), "flexible hours", "elastyczny czas pracy"), //
	WORK_OVERTIME(LessonElement.WORK.getName(), "overtime", "nadgodziny"), //
	WORK_SALARY(LessonElement.WORK.getName(), "salary", "wynagrodzenie"), //
	WORK_EMPLOYER(LessonElement.WORK.getName(), "employer", "pracodawca"), //
	WORK_SHIFT_WORK(LessonElement.WORK.getName(), "shift work", "praca zmianowa"), //
	WORK_TRAINEE(LessonElement.WORK.getName(), "trainee", "stażysta"), //
	WORK_UNEMPLOYED(LessonElement.WORK.getName(), "unemployed", "bezrobotny"), //
	WORK_RETIRING(LessonElement.WORK.getName(), "retiring", "emerytura"), //
	WORK_PROMOTION(LessonElement.WORK.getName(), "promotion", "awans"), //
	WORK_STAFF(LessonElement.WORK.getName(), "staff", "personel"), //
	WORK_EMPLOYEE(LessonElement.WORK.getName(), "employee", "pracownik"), //
	WORK_MATERNITY_LEAVE(LessonElement.WORK.getName(), "maternity leave", "urlop macierzyński"), //
	WORK_JOB_INTERVIEW(LessonElement.WORK.getName(), "job interview", "rozmowa kwalifikacyjna"), //
	WORK_TEMPORARY_JOB(LessonElement.WORK.getName(), "temporary job", "praca tymczasowa"), //
	
	//food
	FOOD_POT(LessonElement.FOOD.getName(), "pot", "garnek"), //
	FOOD_SPOON(LessonElement.FOOD.getName(), "spoon", "łyżka"), //
	FOOD_FORK(LessonElement.FOOD.getName(), "fork", "widelec"), //
	FOOD_KNIFE(LessonElement.FOOD.getName(), "knife", "nóż"), //
	FOOD_CUTLERY(LessonElement.FOOD.getName(), "cutlery", "sztućce"), //
	FOOD_BREAD(LessonElement.FOOD.getName(), "bread", "chleb"), //
	FOOD_BUTTER(LessonElement.FOOD.getName(), "butter", "masło"), //
	FOOD_HAM(LessonElement.FOOD.getName(), "ham", "szynka"), //
	FOOD_SANDWITCH(LessonElement.FOOD.getName(), "sandwich", "kanapka"), //
	FOOD_BREAKFAST(LessonElement.FOOD.getName(), "breakfast", "śniadanie"), //
	FOOD_DINNER(LessonElement.FOOD.getName(), "dinner", "obiad"), //
	FOOD_SUPPER(LessonElement.FOOD.getName(), "supper", "kolacja"), //
	FOOD_SOUP(LessonElement.FOOD.getName(), "soup", "zupa"), //
	FOOD_MEAT(LessonElement.FOOD.getName(), "meat", "mięso"), //
	FOOD_CHICKEN(LessonElement.FOOD.getName(), "chicken", "kurczak"), //
	FOOD_CHEESE(LessonElement.FOOD.getName(), "cheese", "ser"), //
	FOOD_COOK(LessonElement.FOOD.getName(), "cook", "gotować"), //
	FOOD_PEEL(LessonElement.FOOD.getName(), "peel", "obierać"), //
	FOOD_CHOP(LessonElement.FOOD.getName(), "chop", "siekać"), //
	FOOD_POTATOES(LessonElement.FOOD.getName(), "potatoes", "ziemniaki"), //
	FOOD_EGG(LessonElement.FOOD.getName(), "egg", "jajko"), //
	FOOD_CAKE(LessonElement.FOOD.getName(), "cake", "ciasto"), //
	FOOD_BAKE(LessonElement.FOOD.getName(), "bake", "piec"), //
	FOOD_OVEN(LessonElement.FOOD.getName(), "oven", "piekarnik"), //
	FOOD_FRY(LessonElement.FOOD.getName(), "fry", "smażyć"), //
	FOOD_RICE(LessonElement.FOOD.getName(), "rice", "ryż"), //
	FOOD_PASTA(LessonElement.FOOD.getName(), "pasta", "makaron"), //
	FOOD_MEAL(LessonElement.FOOD.getName(), "meal", "posiłek"), //
	FOOD_BEEF(LessonElement.FOOD.getName(), "beef", "wołowina"), //
	FOOD_PORK(LessonElement.FOOD.getName(), "pork", "wieprzowina"), //
	FOOD_PANCAKES(LessonElement.FOOD.getName(), "pancakes", "naleśniki"), //
	;

	private String lessonName;
	private String wordEN;
	private String wordPL;

	private WordElement(String lessonName, String wordEN, String wordPL) {
		this.lessonName = lessonName;
		this.wordEN = wordEN;
		this.wordPL = wordPL;
	}

	public String getLessonName() {
		return lessonName;
	}

	public String getWordEN() {
		return wordEN;
	}

	public String getWordPL() {
		return wordPL;
	}
}
