import java.util.ArrayList;
import java.util.Scanner;
public class ArticleApp {
	
	public static int showMenuAndGetChoice(Scanner sc) {
		System.out.println("Here are your choices: ");
		System.out.println("1. Add an article");
		System.out.println("2. Print articles");
		System.out.println("3. Save articles to JSON");
		System.out.println("4. Delete articles");
		System.out.println("5. Read Articles from JSON");
		System.out.println("6. Exit");
		System.out.print("Enter your choice: ");
		int result = sc.nextInt();
		return result;
	}
	public static void printArticles(ArrayList<Article> articles) {
		for(Article article: articles) {
			System.out.println(article);
		}
	}
	public static void main(String[] args) {
		// This will hold all the articles we create
		ArrayList<Article> articles = new ArrayList<Article>();
		Scanner sc = new Scanner(System.in);
		Scanner st = new Scanner(System.in);
		String title, author, text;
		int choice;
		int toRemove;
		String path;
		ArticleWriter aw = new ArticleWriter();
		ArticleReader ar = new ArticleReader();
		// This do loop will keep offering the user  choices
		//They will be able to create article, print them to the screen
		// remove an article and write to a json
		do {
			choice = showMenuAndGetChoice(sc);
			switch(choice) {
			case(1):
				System.out.print("Enter title: ");
				title = st.nextLine();
				System.out.print("Enter an author: ");
				author = st.nextLine();
				System.out.print("Enter text: ");
				text = st.nextLine();
				articles.add(new Article(title,author,text));
				break;
			case(2):
				printArticles(articles);
				break;
			case(3): // Print JSON
				System.out.print("Enter File path: ");
				path = st.nextLine();
				if(aw.writeToJSON(path, articles)) {
					System.out.println("Wrote sucessfully");
				}
				else {
					System.out.println("An error occured");
				}
				break;
			case(4): // Delete an article
				System.out.print("Enter the number of the article to delete: ");
				toRemove = sc.nextInt();
				articles.remove(toRemove-1); // The arrayList storing the articles begins at an index of 0. Thus, if the user wants to remove the 1st article, the input must be 0
				break;
			case(5): // Delete an article
				System.out.print("Enter the name of the file: ");
				path = st.nextLine();
				articles = ar.readFromJSON(path);
				if (articles ==null) {
					System.out.println("Couldn't read the articles.");
				}
				else {
					System.out.println("Read articles");
				}
				break;
				
			}
			
		}while (choice !=6);
		System.out.println("Thank you.");
	}

}
