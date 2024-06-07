import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostTest {
    // Test cases for addPost()
    @Test
    public void testAddValidPost() {
        // Test data 1: Creating a valid post
        String validBody1 = "valid body ".repeat(60);
        Post post1 = new Post(1, "Sample Post Title", validBody1, new String[] { "tag1", "tag2" }, "Difficult", "Highly Needed");
        assertTrue(post1.addPost(), "Valid post should be added successfully");

        // Test data 2: Another valid post
        String validBody2 = "another valid body ".repeat(30);
        Post post2 = new Post(2, "Another Sample Title", validBody2, new String[] { "tag3", "tag4", "blah" }, "Very Difficult", "Immediately Needed");
        assertTrue(post2.addPost(), "Another valid post should be added successfully");
    }

    @Test
    public void testAddInvalidPostTooShortTitle() {
        // Test data 1: Creating an invalid post with a short title
        String validBody1 = "valid body ".repeat(60);
        Post post1 = new Post(3, "short", validBody1, new String[] { "tag1", "tag2" }, "Difficult", "Highly Needed");
        assertFalse(post1.addPost(), "Post with title too short should not be added");

        // Test data 2: Another invalid post with a short title
        String validBody2 = "another valid body ".repeat(60);
        Post post2 = new Post(4, "invalid", validBody2, new String[] { "tag3", "tag4" }, "Easy", "Ordinary");
        assertFalse(post2.addPost(), "Post with a short title should fail addition");
    }

    @Test
    public void testAddInvalidPostNullTitle() {
        // Test data 1: Creating an invalid post with an empty title
        String validBody1 = "valid body ".repeat(60);
        Post post1 = new Post(3, "", validBody1, new String[] { "tag1", "tag2" }, "Difficult", "Highly Needed");
        assertFalse(post1.addPost(), "Post with lack of title should not be added");

        // Test data 2: Another invalid post with a title consisting of only whitespace
        String validBody2 = "another valid body ".repeat(60);
        Post post2 = new Post(4, "                           ", validBody2, new String[] { "tag3", "tag4" }, "Easy", "Ordinary");
        assertFalse(post2.addPost(), "Post with lack of title should not be added");
    }
    

    @Test
    public void testAddInvalidPostNotEnoughTags() {
        // Test data 1: Creating an invalid post with too little tags (<2)
        String validBody1 = "valid body ".repeat(60);
        Post post1 = new Post(5, "Sample Title", validBody1, new String[] { "tag1" }, "Difficult", "Highly Needed");
        assertFalse(post1.addPost(), "Post with less than 2 tags should not be added");

        // Test data 2: Another invalid post with too little tags (<2)
        String validBody2 = "another valid body ".repeat(60);
        Post post2 = new Post(6, "Another Sample Title", validBody2, new String[] { }, "Easy", "Highly Needed");
        assertFalse(post2.addPost(), "Post with less than 2 tags should not be added");
    }
    
    @Test
    public void testAddInvalidPostTooManyTags() {
        // Test data 1: Creating an invalid post with too many tags (>5)
        String validBody1 = "valid body ".repeat(60);
        Post post1 = new Post(5, "Sample Title", validBody1, new String[] { "tag1", "tag2", "tag3", "tag4", "tag5", "tag6" }, "Difficult", "Highly Needed");
        assertFalse(post1.addPost(), "Post with more than 5 tags should not be added");

        // Test data 2: Another invalid post with too many tags (>3), because type is Easy
        String validBody2 = "another valid body ".repeat(60);
        Post post2 = new Post(6, "Another Sample Title", validBody2, new String[] { "tag1", "tag2", "tag3", "tag4" }, "Easy", "Highly Needed");
        assertFalse(post2.addPost(), "Post with too many tags for 'Easy' type should fail to add");
    }

    @Test
    public void testAddInvalidPostIncompatibleTypeAndEmergency() {
        // Test data 1: Creating an invalid post with incompatible type and emergency level
        String validBody1 = "valid body ".repeat(60);
        Post post1 = new Post(7, "Sample Title", validBody1, new String[] { "tag1", "tag2" }, "Easy", "Immediately Needed");
        assertFalse(post1.addPost(), "Post with mismatched type and urgency should fail");

        // Test data 2: Another invalid post with incompatible type and emergency level
        String validBody2 = "valid body ".repeat(60);
        Post post2 = new Post(8, "Another Sample Title", validBody2, new String[] { "tag3", "tag4" }, "Very Difficult", "Ordinary");
        assertFalse(post2.addPost(), "Post with conflicting type and urgency should not be added");
    }

    // Test cases for addComment()
    @Test
    public void testAddValidComment() {
        // Test data 1: Adding a valid comment
        String validBody1 = "valid body ".repeat(60);
        Post post1 = new Post(9, "Sample Title", validBody1, new String[] { "tag1", "tag2" }, "Difficult", "Highly Needed");
        post1.addPost();
        assertTrue(post1.addComment("This is a valid comment."), "Valid comment should be added successfully");

        // Test data 2: Adding another valid comment
        String validBody2 = "valid body ".repeat(60);
        Post post2 = new Post(10, "Another Sample Title", validBody2, new String[] { "tag3", "tag4" }, "Very Difficult", "Immediately Needed");
        post2.addPost();
        assertTrue(post2.addComment("This is another valid comment."), "Another valid comment should be added successfully");
    }

    @Test
    public void testAddInvalidCommentTooShort() {
        // Test data 1: Adding an invalid comment with too few words
        String validBody1 = "valid body ".repeat(60);
        Post post1 = new Post(11, "Sample Title", validBody1, new String[] { "tag1", "tag2" }, "Difficult", "Highly Needed");
        post1.addPost();
        assertFalse(post1.addComment("Short comment."), "Comment with too few words should not be added");

        // Test data 2: Adding another invalid comment with too few words
        String validBody2 = "another valid body ".repeat(60);
        Post post2 = new Post(12, "Another Sample Title", validBody2, new String[] { "tag3", "tag4" }, "Easy", "Ordinary");
        post2.addPost();
        assertFalse(post2.addComment("Too short."), "Another short comment should not be added");
    }
    
    @Test
    public void testAddInvalidCommentTooLong() {
        // Test data 1: Adding an invalid comment with too many words
        String validBody1 = "valid body ".repeat(60);
        Post post1 = new Post(11, "Sample Title", validBody1, new String[] { "tag1", "tag2" }, "Difficult", "Highly Needed");
        post1.addPost();
        assertFalse(post1.addComment("Long comment long comment long commment long comment long comment long comment."), "Comment with too few words should not be added");

        // Test data 2: Adding another invalid comment with too many words
        String validBody2 = "another valid body ".repeat(60);
        Post post2 = new Post(12, "Another Sample Title", validBody2, new String[] { "tag3", "tag4" }, "Easy", "Ordinary");
        post2.addPost();
        assertFalse(post2.addComment("This comment is too long like way way way way way way way way way way way way too long."), "Another short comment should not be added");
    }


    @Test
    public void testAddInvalidCommentEmpty() {
        // Test data 1: Adding an invalid comment that's empty
        String validBody1 = "valid body ".repeat(60);
        Post post1 = new Post(11, "Sample Title", validBody1, new String[] { "tag1", "tag2" }, "Difficult", "Highly Needed");
        post1.addPost();
        assertFalse(post1.addComment(""), "Comment with too few words should not be added");

        // Test data 2: Adding another invalid comment with only whitespaces
        String validBody2 = "another valid body ".repeat(60);
        Post post2 = new Post(12, "Another Sample Title", validBody2, new String[] { "tag3", "tag4" }, "Easy", "Ordinary");
        post2.addPost();
        assertFalse(post2.addComment("                  "), "Another short comment should not be added");
    }

    

    @Test
    public void testAddInvalidCommentFormat() {
        // Test data 1: Adding an invalid comment with improper formatting
        String validBody1 = "valid body ".repeat(60);
        Post post1 = new Post(13, "Sample Title", validBody1, new String[] { "tag1", "tag2" }, "Difficult", "Highly Needed");
        post1.addPost();
        assertFalse(post1.addComment("this is not Properly Formatted."), "Comment with incorrect formatting should not be added");

        // Test data 2: Adding another invalid comment with improper formatting
        String validBody2 = "valid body ".repeat(60);
        Post post2 = new Post(14, "Another Sample Title", validBody2, new String[] { "tag3", "tag4" }, "Very Difficult", "Immediately Needed");
        post2.addPost();
        assertFalse(post2.addComment("this again is not properly formatted."), "Another comment with incorrect formatting should not be added");
    }

    @Test
    public void testAddInvalidCommentEasyPostLimit() {
        // Test data 1: Adding a comment when the maximum comment length of easy posts is exceeded, making it invalid
        String validBody1 = "valid body ".repeat(60);
        Post post1 = new Post(15, "Sample Title", validBody1, new String[] { "tag1", "tag2" }, "Easy", "Highly Needed");
        post1.addPost();
        post1.addComment("This is Comment 1");
        post1.addComment("This is Comment 2");
        post1.addComment("This is Comment 3");
        assertFalse(post1.addComment("This comment exceeds the limit for an 'Easy' post."), "Comment exceeding limit for 'Easy' post should not be added");

        // Test data 2: Adding a comment when the maximum comment length of easy posts is exceeded, making it invalid
        String validBody2 = "valid body ".repeat(60);
        Post post2 = new Post(16, "Another Sample Title", validBody2, new String[] { "tag3", "tag4" }, "Easy", "Ordinary");
        post2.addPost();
        post2.addComment("This is Comment 1");
        post2.addComment("This is Comment 2");
        post2.addComment("This is Comment 3");
        post1.addComment("This is Comment 4");
        assertFalse(post2.addComment("This comment exceeds the limit for an 'Easy' post."), "Comment exceeding limit for 'Easy' post should not be added");
    }
}
