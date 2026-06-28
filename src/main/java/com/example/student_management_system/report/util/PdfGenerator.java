package com.example.student_management_system.report.util;

import com.example.student_management_system.marks.dto.MarksResponse;
import com.example.student_management_system.report.dto.StudentReportResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PdfGenerator {

    public static byte[] generateStudentReport(
            StudentReportResponse report
    ) {

        try (
                PDDocument document =
                        new PDDocument();

                ByteArrayOutputStream output =
                        new ByteArrayOutputStream()
        ) {

            PDPage page =
                    new PDPage();

            document.addPage(page);

            PDPageContentStream content =
                    new PDPageContentStream(
                            document,
                            page
                    );

            content.setFont(
                    PDType1Font.HELVETICA_BOLD,
                    18
            );

            content.beginText();

            content.newLineAtOffset(
                    50,
                    750
            );

            content.showText(
                    "STUDENT REPORT CARD"
            );

            content.endText();

            content.setFont(
                    PDType1Font.HELVETICA,
                    12
            );

            float y = 720;

            y = writeLine(
                    content,
                    "Student : "
                            + report.getStudentName(),
                    y
            );

            y = writeLine(
                    content,
                    "Email : "
                            + report.getEmail(),
                    y
            );

            y = writeLine(
                    content,
                    "Attendance : "
                            + String.format(
                            "%.2f",
                            report.getAttendancePercentage()
                    ) + "%",
                    y
            );

            y = writeLine(
                    content,
                    "GPA : "
                            + report.getGpa(),
                    y
            );

            y -= 20;

            y = writeLine(
                    content,
                    "MARKS",
                    y
            );

            y -= 10;

            for (
                    MarksResponse mark :
                    report.getMarks()
            ) {

                String line =
                        mark.getCourseName()
                                + " | "
                                + mark.getSubject()
                                + " | "
                                + mark.getScore()
                                + " | "
                                + mark.getGrade();

                y = writeLine(
                        content,
                        line,
                        y
                );
            }

            content.close();

            document.save(output);

            return output.toByteArray();

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to generate PDF",
                    e
            );
        }
    }

    private static float writeLine(
            PDPageContentStream content,
            String text,
            float y
    ) throws IOException {

        content.beginText();

        content.newLineAtOffset(
                50,
                y
        );

        content.showText(text);

        content.endText();

        return y - 20;
    }
}
