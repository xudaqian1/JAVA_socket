import javax.swing.JFileChooser;
public class chose_floder {
    public String find() {
        String path=null;
        JFileChooser fc = new JFileChooser("C:");
        //是否可多选
        fc.setMultiSelectionEnabled(false);
        //选择模式，可选择文件和文件夹
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
//      fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
//      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //设置是否显示隐藏文件
        fc.setFileHidingEnabled(true);
        fc.setAcceptAllFileFilterUsed(false);
        //设置文件筛选
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
             path=fc.getSelectedFile().toString();
            //fc.getSelectedFiles()
        }
        return path;
    }

}