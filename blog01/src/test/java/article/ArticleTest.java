package article;

import java.util.Random;

import org.junit.Test;

import com.java1234.lucha.service.ArticleService;
import com.java1234.lucha.util.Article;
import com.java1234.lucha.util.User;

public class ArticleTest {
	private ArticleService service = new ArticleService();
	private int getNum(int min , int max){
		Random random = new Random();
		return random.nextInt(max)%(max-min+1) + min;
	}
	
	@Test
	public void postTest(){
		for (int i=0; i < 10 ; i++) {
			Article article = new Article();
			article.setContent("我今天很开心" + i);
			article.setVotes(Long.valueOf(getNum(0,100)));
			article.setWriter("作者" + i);
			User user = new User();
			user.setId(i);
			service.postArt(article, user);
		}
	}
	
	@Test
	public void voteTest(){
		Article article = new Article();
		article.setId(Long.valueOf(1001));
		User user = new User();
		user.setId(11);
		service.voteArticle(article, user);
	}
	
	@Test
	public void getByScore(){
		String artsByScore = service.getArtsByScore();
		System.out.println(artsByScore);
	}
	
	@Test
	public void getByTime(){
		String artsByScore = service.getArtsByPostTime();
		System.out.println(artsByScore);
	}

}
