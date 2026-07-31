import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImp implements BookDAO{
    @Override
    public void save(Book book) {
        String sql = "INSERT INTO books VALUES (?, ?, ?);";

        try(Connection conn = ConnectionsFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, book.getId());
            prep.setString(2, book.getTitle());
            prep.setString(3, book.getAuthor());

            prep.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Book getById(int id) {
        String sql = "SELECT * FROM book WHERE id = ?;";

        try(Connection conn = ConnectionsFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, id);

            ResultSet result = prep.executeQuery();
            while(result.next()) {
                return new Book(result.getInt(1),
                        result.getString(2),
                        result.getString(3));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>(10);
        String sql = "SELECT * FROM book;";

        try(Connection conn = ConnectionsFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            ResultSet result = prep.executeQuery();

            while(result.next()) {
                Book book = new Book(result.getInt(1),
                        result.getString(2),
                        result.getString(3));

                books.add(book);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE books SET title = ?, author = ? WHERE id = ?;";

        try(Connection conn = ConnectionsFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, book.getTitle());
            prep.setString(2, book.getAuthor());
            prep.setInt(3, book.getId());

            prep.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?;";

        try(Connection conn = ConnectionsFactory.getInstance().getConnection()){
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setInt(1, id);

            prep.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
