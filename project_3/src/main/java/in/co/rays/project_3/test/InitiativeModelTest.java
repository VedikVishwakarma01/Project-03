package in.co.rays.project_3.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project_3.dto.InitiativeDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.model.InitiativeModelHibImp;
import in.co.rays.project_3.model.InitiativeModelInt;

public class InitiativeModelTest {

    public static InitiativeModelInt model = new InitiativeModelHibImp();

    public static void main(String[] args) throws Exception {
        addTest();
        // updateTest();
        // deleteTest();
        // findByPKTest();
        // listTest();
        // searchTest();
    }

    public static void addTest() throws Exception {
        InitiativeDTO dto = new InitiativeDTO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        dto.setInitiativeName("New Initiative");
        dto.setType("Innovation");
        dto.setStartDate(sdf.parse("2025-01-01"));
        dto.setVersion(1);
        dto.setCreatedBy("admin");
        dto.setModifiedBy("admin");
        dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
        dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

        long pk = model.add(dto);
        System.out.println("Initiative added successfully with PK: " + pk);
    }

    public static void updateTest() throws Exception {
        InitiativeDTO dto = new InitiativeDTO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        dto.setId(1L); // set the existing initiative ID
        dto.setInitiativeName("Updated Initiative");
        dto.setType("Technology");
        dto.setStartDate(sdf.parse("2025-02-01"));
        dto.setVersion(2);
        dto.setCreatedBy("admin");
        dto.setModifiedBy("admin");
        dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
        dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

        model.update(dto);
        System.out.println("Initiative updated successfully");
    }

    public static void deleteTest() throws ApplicationException {
        InitiativeDTO dto = new InitiativeDTO();
        dto.setId(1L); // set the initiative ID to delete

        model.delete(dto);
        System.out.println("Initiative deleted successfully");
    }

    public static void findByPKTest() throws ApplicationException {
        InitiativeDTO dto = model.findByPK(1L);

        if (dto != null) {
            System.out.println(dto.getId() + "\t" + dto.getInitiativeName() + "\t" + dto.getType() + "\t"
                    + dto.getStartDate() + "\t" + dto.getVersion() + "\t" + dto.getCreatedBy() + "\t"
                    + dto.getModifiedBy() + "\t" + dto.getCreatedDatetime() + "\t" + dto.getModifiedDatetime());
        } else {
            System.out.println("Record not found");
        }
    }

    public static void listTest() throws ApplicationException {
        List<InitiativeDTO> list = model.list(1, 10);

        if (list.isEmpty()) {
            System.out.println("List is empty");
        }

        Iterator<InitiativeDTO> it = list.iterator();
        while (it.hasNext()) {
            InitiativeDTO dto = it.next();
            System.out.println(dto.getId() + "\t" + dto.getInitiativeName() + "\t" + dto.getType() + "\t"
                    + dto.getStartDate() + "\t" + dto.getVersion() + "\t" + dto.getCreatedBy() + "\t"
                    + dto.getModifiedBy() + "\t" + dto.getCreatedDatetime() + "\t" + dto.getModifiedDatetime());
        }
    }

    public static void searchTest() throws ApplicationException {
        InitiativeDTO dto = new InitiativeDTO();
        dto.setType("Innovation"); // example search criteria

        ArrayList<InitiativeDTO> list = (ArrayList<InitiativeDTO>) model.search(dto, 0, 0);

        for (InitiativeDTO d : list) {
            System.out.println(d.getId() + "\t" + d.getInitiativeName() + "\t" + d.getType() + "\t"
                    + d.getStartDate() + "\t" + d.getVersion() + "\t" + d.getCreatedBy() + "\t"
                    + d.getModifiedBy() + "\t" + d.getCreatedDatetime() + "\t" + d.getModifiedDatetime());
        }
    }
}
