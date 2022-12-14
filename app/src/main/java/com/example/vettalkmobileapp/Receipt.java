package com.example.vettalkmobileapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Receipt extends AppCompatActivity{

    ImageView notification;
    ImageView menu;
    Button btn_dl_pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt);
        btn_dl_pdf = findViewById(R.id.btn_dl_pdf);

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        btn_dl_pdf.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                createPDFFile(Common.getAppPath(Receipt.this)+"test_pdf.pdf");
                            }
                        });
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();


        notification = findViewById(R.id.imageView2);
        menu = findViewById(R.id.imageView3);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Receipt.this, Notifications.class);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Receipt.this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.menupage);
                dialog.show();
            }
        });
    }

    public void menuboard(View v) {
        Intent intent = new Intent(Receipt.this, Success.class);
        startActivity(intent);
    }

    private void createPDFFile(String path) {
        if (new File(path).exists())
            new File(path).delete();
        try {
            Document document = new Document();
            //Save
            PdfWriter.getInstance(document,new FileOutputStream(path));

            //Open to write
            document.open();

            //Setting
            document.setPageSize(PageSize.A4);
            document.addCreationDate();
            document.addAuthor("VetTalk");

            //Font Setting
            BaseColor colorAccent = new BaseColor(0,153,204,255);
            float fontSize = 20.0f;
            float valueFontSize = 26.0f;

            //Custom Font
            BaseFont fontName = BaseFont.createFont("assets/fonts/brandon_medium.ttf","UTF-8",BaseFont.EMBEDDED);

            //Create Title of Document
            Font titleFont = new Font(fontName,36.0f, Font.NORMAL, BaseColor.BLACK);
            addNewItem(document,"Receipt", Element.ALIGN_CENTER,titleFont);

            //Add more
            Font transacNumberFont = new Font(fontName, fontSize, Font.NORMAL, colorAccent);
            addNewItem(document, "Transaction # ", Element.ALIGN_LEFT, transacNumberFont);

            Font transacNumberValueFont = new Font(fontName, valueFontSize, Font.NORMAL, BaseColor.BLACK);
            addNewItem(document, "", Element.ALIGN_LEFT, transacNumberValueFont);

            addLineSeparator(document);

            addNewItem(document, "Transaction Date: ", Element.ALIGN_LEFT, transacNumberFont);
            addNewItem(document, "", Element.ALIGN_LEFT, transacNumberValueFont);

            addLineSeparator(document);

            addNewItem(document, "Owner Name: ", Element.ALIGN_LEFT, transacNumberFont);
            addNewItem(document, "", Element.ALIGN_LEFT, transacNumberValueFont);

            addLineSeparator(document);

            //Products and Services
            addLineSpace(document);
            addNewItem(document, "Products and Services", Element.ALIGN_LEFT, titleFont);
            addLineSeparator(document);

            //Item 1
            addNewItems(document, "", "", titleFont, transacNumberValueFont);
            addNewItems(document, "", "", titleFont, transacNumberValueFont);

            addLineSeparator(document);

            //Item 2
            addNewItems(document, "", "", titleFont, transacNumberValueFont);
            addNewItems(document, "", "", titleFont, transacNumberValueFont);

            addLineSeparator(document);

            //Total
            addLineSpace(document);
            addLineSpace(document);

            addNewItems(document, "Total: ", "", titleFont, transacNumberValueFont);

            document.close();

            Toast.makeText(this, "Transaction Successful!", Toast.LENGTH_SHORT).show();

            printPDF();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printPDF() {
        PrintManager printManager = (PrintManager)getSystemService(Context.PRINT_SERVICE);
        try{
            PrintDocumentAdapter printDocumentAdapter = new PdfDocumentAdapter(Receipt.this,Common.getAppPath(Receipt.this)+"test_pdf.pdf");
            printManager.print("Document", printDocumentAdapter, new PrintAttributes.Builder().build());
        } catch (Exception ex)
        {
            Log.e("VetTalk", ""+ex.getMessage());
        }
    }

    private void addNewItems(Document document, String textLeft, String textRight, Font textLeftFont, Font textRightFont) throws DocumentException {
        Chunk chunkTextLeft = new Chunk (textLeft, textLeftFont);
        Chunk chunkTextRight = new Chunk (textRight, textRightFont);
        Paragraph p = new Paragraph(chunkTextLeft);
        p.add(new Chunk(new VerticalPositionMark()));
        p.add(chunkTextRight);
        try {
            document.add(p);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void addLineSeparator(Document document) throws DocumentException {
        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(new BaseColor(0,0,0,68));
        addLineSpace(document);
        document.add(new Chunk(lineSeparator));
        addLineSpace(document);
    }

    private void addLineSpace(Document document) throws DocumentException {
        document.add(new Paragraph(""));
    }

    private void addNewItem(Document document, String text, int align, Font font) throws DocumentException
    {
        Chunk chunk = new Chunk(text,font);
        Paragraph paragraph = new Paragraph(chunk);
        paragraph.setAlignment(align);
        document.add(paragraph);
    }
}



