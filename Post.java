package SEFAssignment4;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Post {
    private int postID;
    private String postTitle;
    private String postBody;
    private String [] postTags;
    private String postType;
    private String postEmergency;
    private ArrayList <String> postComments = new ArrayList<>();
    
    public Post(int postID, String postTitle, String postBody, String[] postTags, String postType, String postEmergency) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postType = postType;
        this.postEmergency = postEmergency;
    }

    public boolean addPost()
    {
        // Validate the post before adding
        if (postTitle.length() < 10 || postTitle.length() > 250) {
            System.out.println("Title must be between 10 and 250 characters");
            return false;
        }
        else if (postTitle.matches("^[0-9\\W]{5}.*")) {
            System.out.println("Title must not have special characters in the first 5 digits");
            return false;
        }
        if (postBody.length() < 250) {
            System.out.println("Body must be over 250 characters");
            return false;
        }
        if (postTags.length < 2 || postTags.length > 5) {
            System.out.println("Post must have a minimum of 2 tags and a maximum of 5 tags");
            return false;
        }
        for (int i = 0; i < postTags.length; i++) {
            if (postTags[i].length() < 2 || postTags[i].length() > 10) {
                System.out.println("Post tag must have a minimum of 2 characters and a maximum of 10 characters");
                return false;
            }
            else if (postTags[i].matches(".*[A-Z].*")) {
                System.out.println("Post tag must have no upper case characters");
                return false;
            }
        }
        
        if (postType.equals("Easy") && postTags.length > 3) {
            System.out.println("Easy posts should not have more than 3 tags");
            return false;
        }
        else if ((postType.equals("Very Difficult") || postType.equals("Difficult")) && postBody.length() < 300) {
            System.out.println("Post body must have minimum length of 300 if post type is Very Difficult or Difficult");
            return false;
        }

        if (postType.equals("Easy") && (postEmergency.equals("Immediately Needed") || postEmergency.equals("Highly Needed"))) {
            System.out.println("Post emergency must not be Immediately Needed or Highly Needed if post type is Easy");
            return false;
        }
        else if ((postType.equals("Very Difficult") || postType.equals("Difficult")) && postEmergency.equals("Ordinary")) {
            System.out.println("Post emergency must not be ordinary if post type is Very Difficult or Difficult");
            return false;
        }
        File postFile = new File("thread.txt");
        try {
            if (!postFile.exists()) {
                System.out.println("file does not exist.");
                postFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try (FileWriter writer = new FileWriter("thread.txt", true)) {
            writer.write("---\n");
            writer.write("Post ID: " + postID + "\n");
            writer.write("Post Title: " + postTitle + "\n");
            writer.write("Post Body: " + postBody + "\n");
            writer.write("Post Tags: ");
            for (String tag : postTags) {
                writer.write(tag + " ");
            }
            writer.write("\n");
            writer.write("Post Type: " + postType + "\n");
            writer.write("Post Emergency Level: " + postEmergency + "\n");
            writer.write("Post Comments: ");
            for (String comment : postComments) {
                writer.write(comment + " ");
            }
            writer.write("\n");
            writer.write("---\n");
            writer.write("\n");
            System.out.println("Post added with ID " + postID);
            return true;
        } catch (IOException e) { // If error with writing to file
            e.printStackTrace();
            System.out.println("Error adding post.");
            return false;
        }
    }
    public boolean addComment(String commentBody)
    {
        String[] commentWords = commentBody.split(" ");
        int commentWordLength = commentWords.length;
        if (commentWordLength < 4 || commentWordLength > 10) {
            System.out.println("The comment text should be a minimum of 4 words and a maximum of 10 words");
            return false;
        }
        else if (!Character.isUpperCase(commentWords[0].charAt(0))) {
            System.out.println("The comment's first character should be an uppercase letter");
            return false;
        }
        if((postType.equals("Very Difficult") || postType.equals("Difficult")) && postComments.size() >= 5) {
            System.out.println("Very Difficult and Difficult posts can only have up to 5 comments");
            return false;
        }
        else if(postType.equals("Easy") && postComments.size() >= 3) {
            System.out.println("Easy posts can only have up to 3 comments");
            return false;
        }
        postComments.add(commentBody);

        File postFile = new File("thread.txt");
        try {
            if (!postFile.exists()) {
                System.out.println("file does not exist.");
                postFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        try {
            StringBuilder fileContent = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader("thread.txt"));
            String line;
            boolean foundPost = false;
            boolean foundComments = false;
            while ((line = reader.readLine()) != null) { // Checks each line for post ID, then post comments section,
                                                         // then bottom of post
                fileContent.append(line).append("\n");
                if (line.equals("Post ID: " + postID)) {
                    foundPost = true;
                }
                if (foundPost && line.trim().equals("Post Comments:")) {
                    foundComments = true;
                }
                if (foundComments && line.trim().equals("---")) {
                    fileContent.insert(fileContent.lastIndexOf("Post Comments:") + "Post Comments:".length(),
                            "\n" + commentBody); // Inserts a comment after the "Post Comments" bit on a new line.
                    foundPost = false;
                    foundComments = false;
                }
            }
            reader.close(); // Close BufferedReader after inserting comment

            BufferedWriter writer = new BufferedWriter(new FileWriter("thread.txt"));
            writer.write(fileContent.toString());
            writer.close();
            System.out.println("Comment added. There are now " + postComments.size() + " comments");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Return false if error adding comment
        }
    }
}
