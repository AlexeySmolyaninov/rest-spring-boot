package scoreservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alexey Smolyaninov
 */

@RestController
public class ScoreController {
    
    @Autowired
    private ScoreRepository scoreRepository;
    
    @Autowired
    private GameRepository gameRepository;
    
    @GetMapping("/games/{name}/scores")
    public List<Score> listScores(@PathVariable String name){
        Game game = gameRepository.findByName(name);
        if(game != null){
            return scoreRepository.findByGame(game);
        }
        return null;
    }
    
    @GetMapping("/games/{name}/scores/{id}")
    public Score getScore(
            @PathVariable String name,
            @PathVariable Long id){
        Game game = gameRepository.findByName(name);
        if(game != null){
            Score score = scoreRepository.getOne(id);
            score.setGame(game);
            return score;
        }
        return null;
    }
    
    @PostMapping("/games/{name}/scores")
    public Score createScore(
            @PathVariable String name,
            @RequestBody Score score){
        Game game = gameRepository.findByName(name);
        if(game != null){
            score.setGame(game);
            scoreRepository.save(score);
            return score;
        }
        return null;
    }
    
    @DeleteMapping("/games/{name}/scores/{id}")
    public Score deleteScore(
            @PathVariable String name,
            @PathVariable Long id){
        Game game = gameRepository.findByName(name);
        if(game != null){
            Score score = scoreRepository.getOne(id);
            score.setGame(game);
            scoreRepository.delete(score);
            return score;
        }
        return null;
    }
}
